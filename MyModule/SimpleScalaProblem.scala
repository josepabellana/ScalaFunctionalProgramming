// A comment!
/* Another comment */
/** A documentation comment */
object MyModule { // Declares a singleton object, which simultaneously declares a class and its only instance
    def abs(n: Int): Int =
        if (n < 0) -n
        else n
    
    private def formatAbs(x: Int) = { // The body of the method contains more than one statement, so we put them inside curly braces.
        val msg = "The absolute value of %d is %d"
        msg.format(x, abs(x)) // Replaces the two %d placeholders in the string with x and abs(x) respectively.
    }

    def main(args: Array[String]): Unit = // Unit serves the same purpose as void in languages like Java or C
        println(formatAbs(-42))
}

// object is also known as module

// the definition of a methos is called left-hand side or signature -> the definition of the method is called right-hand side or definition
// the value returnes from a method is simply whatever value results from evaluation the righ-hand side.

// The name main is special because when you run a program, Scala will look for a method named main with a specific signature.