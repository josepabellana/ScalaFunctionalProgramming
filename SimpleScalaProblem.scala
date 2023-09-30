// A comment!
/* Another comment */
/** A documentation comment */
object MyModule { // Declares a singleton object, which simultaneously declares a class and its only instance
    def abs(n: Int): Int =
        if (n < 0) -n
        else n
    
}