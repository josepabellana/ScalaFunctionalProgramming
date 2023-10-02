def factorial(n: Int): Int = {
    def go(n: Int, acc: Int): Int = // Inner function or local definition, Common in Scala to write functions that are local to the body of another func.
        if (n <= 0) acc
        else  go(n-1, n*acc)

    go(n, 1)
}

/*
Tail calls in Scala
A call is said to be in tail position if the caller does nothing other than return the value of the recursive call. 
For example, the recursive call to go(n-1,n*acc) we discussed earlier is in tail position, 
since the method returns the value of this recursive call directly and does nothing else with it. 
On the other hand, if we said 1 + go(n-1,n*acc), go would no longer be in tail position, 
since the method would still have work to do when go returned its result (namely, adding 1 to it).
If all recursive calls made by a function are in tail position, 
Scala automatically com- piles the recursion to iterative loops that don’t consume call stack frames for each iteration. 
By default, Scala doesn’t tell us if tail call elimination was successful, 
but if we’re expecting this to occur for a recursive function we write, we can tell the Scalacompiler about this assumption using the tailrec annotation (http://mng.bz/ bWT5), 
so it can give us a compile error if it’s unable to eliminate the tail calls of the function. Here’s the syntax for this:
def factorial(n: Int): Int = { 
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
        if (n <= 0) acc
        else go(n-1, n*acc) 
    go(n, 1)
}
We won’t talk much more about annotations in this book (you’ll find more information at http://mng.bz/GK8T), but we’ll use @annotation.tailrec extensively.
*/

// Exercise 2.1 - Recursive Fibonacci number

def fib(n: Int): Int = {
  @annotation.tailrec
  def fib_tail(n: Int, a: Int, b: Int): Int = n match {
    case 0 => a
    case _ => fib_tail(n - 1, b, a + b)
  }
  return fib_tail(n, 0 , 1)
}

// to run this functions:
// > scala
// > :load HOF.scala
// > fib(x)