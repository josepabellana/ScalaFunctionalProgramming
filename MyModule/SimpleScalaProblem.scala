// A comment!
/* Another comment */
/** A documentation comment */
// scalac SimpleScalaProblem.scala to convert to java class
object MyModule { // Declares a singleton object, which simultaneously declares a class and its only instance
    def factorial(n: Int): Int = {
        def go(n: Int, acc: Int): Int = // Inner function or local definition, Common in Scala to write functions that are local to the body of another func.
            if (n <= 0) acc
            else  go(n-1, n*acc)
        go(n, 1)
    }

    def abs(n: Int): Int =
        if (n < 0) -n
        else n
    
    private def formatResult(name: String, n: Int, f: Int => Int) = { // The body of the method contains more than one statement, so we put them inside curly braces.
        val msg = "The %s of %d is %d"
        msg.format(name, n, f(n)) // Replaces the three %d placeholders in the string with name, n and f(n) respectively.
    }


    def main(args: Array[String]): Unit = // Unit serves the same purpose as void in languages like Java or C
        println(formatResult("absolute value", -42, abs))
        println(formatResult("factorial value", 7, factorial))
}

// object is also known as module

// the definition of a methos is called left-hand side or signature -> the definition of the method is called right-hand side or definition
// the value returnes from a method is simply whatever value results from evaluation the righ-hand side.

// The name main is special because when you run a program, Scala will look for a method named main with a specific signature.