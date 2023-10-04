# ScalaFunctionalProgramming

*Functional programming* means: Coding with pure functions with no side effects such as reading from files or mutating memory.

List of side effects:
 Modifying a variable
 Modifying a data structure in place
 Setting a field on an object
 Throwing an exception or halting with an error  Printing to the console or reading user input
 Reading from or writing to a file
 Drawing on the screen

*Referential transparency* forces the invariant that everything a function does is rep- resented by the value that it returns, according to the result type of the function.

The simplest way we can run this Scala program (MyModule) is from the command line, by invoking the Scala compiler directly ourselves. We start by putting the code in a file called MyModule.scala or something similar. We can then compile it to Java bytecode using the scalac compiler:
        > scalac MyModule.scala
This will generate some files ending with the .class suffix. These files contain com- piled code that can be run with the Java Virtual Machine (JVM). The code can be exe- cuted using the scala command-line tool:
        > scala MyModule
        The absolute value of -42 is 42.

Actually, it’s not strictly necessary to compile the code first with scalac. A simple pro- gram like the one we’ve written here can be run using just the Scala interpreter by passing it to the scala command-line tool directly:
        > scala MyModule.scala
The absolute value of -42 is 42.
This can be handy when using Scala for scripting. The interpreter will look for any object within the file MyModule.scala that has a main method with the appropriate signature, and will then call it.

> scala
Welcome to Scala.
Type in expressions to have them evaluated.
Type :help for more information.
scala> :load MyModule.scala
Loading MyModule.scala...
defined module MyModule
scala> MyModule.abs(-42)
res0: Int = 42


Note that even an expression like 2 + 1 is just calling a member of an object. In that case, what we’re calling is the + member of the object 2. It’s really syntactic sugar for the expression 2.+(1), which passes 1 as an argument to the method + on the object 2.


For example, instead of MyModule.abs(42) we can say MyModule abs 42 and get the same result. You can use whichever you find more pleasing in any given case.
We can bring an object’s member into scope by importing it, which allows us to call it unqualified from then on:
        scala> import MyModule.abs
        import MyModule.abs
        scala> abs(-42)
        res0: 42
We can bring all of an object’s (nonprivate) members into scope by using the under- score syntax:
        import MyModule._
