
class Pos:
	def __init__(self, myindex, line_num, colum, file_name, all_text):
		self.myindex = myindex
		self.line_num = line_num
		self.colum = colum
		self.file_name = file_name
		self.all_text = all_text

	def nextCh(self, current_char=None):
		self.myindex += 1
		self.colum += 1

		if current_char == '\n':
			self.line_num += 1
			self.colum = 0

		return self

	def copy(self):
		return Pos(self.myindex, self.line_num, self.colum, self.file_name, self.all_text)


my_int			= 'INT'
my_flot    = 'FLOAT'
my_plu     = 'PLUS'
my_min    = 'MINUS'
my_mul      = 'MUL'
my_div      = 'DIV'
my_lbracket   = 'LPAREN'
my_rbracket   = 'RPAREN'
my_eof			= 'EOF'

class Token:
	def __init__(self, type_, value=None, start=None, end=None):
		self.type = type_
		self.value = value

		if start:
			self.start = start.copy()
			self.end = start.copy()
			self.end.nextCh()

		if end:
			self.end = end
	
	def __repr__(self):
		if self.value: 
                    return f'{self.type}:{self.value}'
		return f'{self.type}'

Numbers = '0123456789'
# LEXER
class Lexer:
	def __init__(self, file_name, text):
		self.file_name = file_name
		self.text = text
		self.pos = Pos(-1, 0, -1, file_name, text)
		self.current_char = None
		self.nextCh()
	
	def nextCh(self):
		self.pos.nextCh(self.current_char)
		self.current_char = self.text[self.pos.myindex] if self.pos.myindex < len(self.text) else None

	def make_tokens(self):
		tokens = []

		while self.current_char != None:
			if self.current_char in ' \t':
				self.nextCh()
			elif self.current_char in Numbers:
				tokens.append(self.find_type())
			elif self.current_char == '+':
				tokens.append(Token(my_plu, start=self.pos))
				self.nextCh()
			elif self.current_char == '-':
				tokens.append(Token(my_min, start=self.pos))
				self.nextCh()
			elif self.current_char == '*':
				tokens.append(Token(my_mul, start=self.pos))
				self.nextCh()
			elif self.current_char == '/':
				tokens.append(Token(my_div, start=self.pos))
				self.nextCh()
			elif self.current_char == '(':
				tokens.append(Token(my_lbracket, start=self.pos))
				self.nextCh()
			elif self.current_char == ')':
				tokens.append(Token(my_rbracket, start=self.pos))
				self.nextCh()
			

		tokens.append(Token(my_eof, start=self.pos))
		return tokens, None

	def find_type(self):
		my_num = ''
		point = 0
		start = self.pos.copy()

		while self.current_char != None and self.current_char in Numbers + '.':
			if self.current_char == '.':
				if point == 1: break
				point += 1
				my_num += '.'
			else:
				my_num += self.current_char
			self.nextCh()

		if point == 0:
			return Token(my_int, int(my_num), start, self.pos)
		else:
			return Token(my_flot, float(my_num), start, self.pos)



class NumberNode:
	def __init__(self, tok):
		self.tok = tok

		self.start = self.tok.start
		self.end = self.tok.end

	def __repr__(self):
		return f'{self.tok}'

class BinOpNode:
	def __init__(self, left_node, op_tok, right_node):
		self.left_node = left_node
		self.op_tok = op_tok
		self.right_node = right_node

		self.start = self.left_node.start
		self.end = self.right_node.end

	def __repr__(self):
		return f'({self.left_node}, {self.op_tok}, {self.right_node})'

class UnaryOpNode:
	def __init__(self, op_tok, node):
		self.op_tok = op_tok
		self.node = node

		self.start = self.op_tok.start
		self.end = node.end

	def __repr__(self):
		return f'({self.op_tok}, {self.node})'


# PARSER

class Parsevalue:
	def __init__(self):
		self.error = None
		self.node = None

	def register(self, res):
		if isinstance(res, Parsevalue):
			if res.error: self.error = res.error
			return res.node

		return res

	def success(self, node):
		self.node = node
		return self

	def failure(self, error):
		self.error = error
		return self

class Parser:
	def __init__(self, tokens):
		self.tokens = tokens
		self.tok_myindex = -1
		self.nextCh()

	def nextCh(self, ):
		self.tok_myindex += 1
		if self.tok_myindex < len(self.tokens):
			self.current_tok = self.tokens[self.tok_myindex]
		return self.current_tok

	def parse(self):
		res = self.expr()
		if not res.error and self.current_tok.type != my_eof:
			return res.failure(InvalidSyntaxError(self.current_tok.start, self.current_tok.end,"Expected '+', '-', '*' or '/'"))
		return res

	def factor(self):
		res = Parsevalue()
		tok = self.current_tok

		if tok.type in (my_plu, my_min):
			res.register(self.nextCh())
			factor = res.register(self.factor())
			if res.error: return res
			return res.success(UnaryOpNode(tok, factor))
		
		elif tok.type in (my_int, my_flot):
			res.register(self.nextCh())
			return res.success(NumberNode(tok))

		elif tok.type == my_lbracket:
			res.register(self.nextCh())
			expr = res.register(self.expr())
			if res.error: return res
			if self.current_tok.type == my_rbracket:
				res.register(self.nextCh())
				return res.success(expr)
			else:
				return res.failure(InvalidSyntaxError(
					self.current_tok.start, self.current_tok.end,
					"Expected ')'"
				))

		return res.failure(InvalidSyntaxError(
			tok.start, tok.end,
			"Expected int or float"
		))

	def term(self):
		return self.bin_op(self.factor, (my_mul, my_div))

	def expr(self):
		return self.bin_op(self.term, (my_plu, my_min))

	###################################

	def bin_op(self, func, ops):
		res = Parsevalue()
		left = res.register(func())
		if res.error: return res

		while self.current_tok.type in ops:
			op_tok = self.current_tok
			res.register(self.nextCh())
			right = res.register(func())
			if res.error: return res
			left = BinOpNode(left, op_tok, right)

		return res.success(left)



class RTvalue:
	def __init__(self):
		self.value = None
		self.error = None

	def register(self, res):
		if res.error: self.error = res.error
		return res.value

	def success(self, value):
		self.value = value
		return self

	def failure(self, error):
		self.error = error
		return self


class Number:
	def __init__(self, value):
		self.value = value
		self.set_pos()
		self.set_context()

	def set_pos(self, start=None, end=None):
		self.start = start
		self.end = end
		return self

	def set_context(self, context=None):
		self.context = context
		return self

	def added_to(self, other):
		if isinstance(other, Number):
			return Number(self.value + other.value).set_context(self.context), None

	def subbed_by(self, other):
		if isinstance(other, Number):
			return Number(self.value - other.value).set_context(self.context), None

	def multed_by(self, other):
		if isinstance(other, Number):
			return Number(self.value * other.value).set_context(self.context), None

	def dived_by(self, other):
		if isinstance(other, Number):
			if other.value == 0:
				pass

			return Number(self.value / other.value).set_context(self.context), None

	def __repr__(self):
		return str(self.value)


# INTERPRETER
class Context:
	def __init__(self, display_name, parent=None, parent_entry_pos=None):
		self.display_name = display_name
		self.parent = parent
		self.parent_entry_pos = parent_entry_pos

class Interpreter:
	def visit(self, node, context):
		method_name = f'visit_{type(node).__name__}'
		method = getattr(self, method_name, self.no_visit_method)
		return method(node, context)

	def no_visit_method(self, node, context):
		raise Exception(f'No visit_{type(node).__name__} method defined')

	def visit_NumberNode(self, node, context):
		return RTvalue().success(
			Number(node.tok.value).set_context(context).set_pos(node.start, node.end)
		)

	def visit_BinOpNode(self, node, context):
		res = RTvalue()
		left = res.register(self.visit(node.left_node, context))
		if res.error: return res
		right = res.register(self.visit(node.right_node, context))
		if res.error: return res

		if node.op_tok.type == my_plu:
			value, error = left.added_to(right)
		elif node.op_tok.type == my_min:
			value, error = left.subbed_by(right)
		elif node.op_tok.type == my_mul:
			value, error = left.multed_by(right)
		elif node.op_tok.type == my_div:
			value, error = left.dived_by(right)

		if error:
			return res.failure(error)
		else:
			return res.success(value.set_pos(node.start, node.end))

	def visit_UnaryOpNode(self, node, context):
		res = RTvalue()
		number = res.register(self.visit(node.node, context))
		if res.error: return res

		error = None

		if node.op_tok.type == my_min:
			number, error = number.multed_by(Number(-1))

		if error:
			return res.failure(error)
		else:
			return res.success(number.set_pos(node.start, node.end))






def InvalidSyntaxError(self):
	print("Envalid syntax")


def run(file_name, text):
	# Generate tokens
	lexer = Lexer(file_name, text)
	tokens, error = lexer.make_tokens()
	if error: return None, error
	
	# Generate AST
	parser = Parser(tokens)
	ast = parser.parse()
	if ast.error: return None, ast.error

	# Run program
	interpreter = Interpreter()
	context = Context('<program>')
	value = interpreter.visit(ast.node, context)

	return value.value, value.error

