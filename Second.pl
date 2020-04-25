#In static scoping, it always reference to the global variable 
#In dynamic scoping, it always refernce to the local variable

$name = "Mark";

sub sta{
    
    print "$name \n"; 
}

sub dyna{
    local $name = "John";   #local keyword is make you create local variable inside the function
    print $name;
}

sta();
dyna();