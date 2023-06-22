
// Evaluating Rules
/ *
* Call by value: evaluates the function arguments before calling the function
* Call by name: evaluates the function first, and then evaluates the arguments if need be
* /

def example = 2      // evaluated when called
val example = 2      // evaluated immediately
lazy val example = 2 // evaluated once when needed

def square(x: Double)    // call by value
def square(x: => Double) // call by name
def myFct(bindings: Int*) =  ...  // bindings is a sequence of int, containing a varying # of argume




/ * High order functions
* These are functions that take a function as a parameter or return functions.
* sum takes a function that takes an integer and returns an integer then
* returns a function that takes two integers and returns an integer
* / 
def sum(f: Int => Int): (Int, Int) => Int =
  def sumf(a: Int, b: Int): Int = f(a) + f(b)
  sumf

// same as above. Its type is (Int => Int) => (Int, Int) => Int
def sum(f: Int => Int)(a: Int, b: Int): Int =  ...

// Called like this
sum((x: Int) => x * x * x)          // Anonymous function, i.e. does not have a name
sum(x => x * x * x)                 // Same anonymous function with type inferred

def cube(x: Int) = x * x * x
sum(x => x * x * x)(1, 10) // sum of 1 cubed and 10 cubed
sum(cube)(1, 10)           // same as above  



/ *
* Currying
* Converting a function with multiple arguments into a function with a single argument that returns another function.
* /

val f2: (Int, Int) => Int = f // uncurried version (type is (Int, Int) => Int)
val f3: Int => Int => Int = f2.curried // transform it to a curried version (type is Int => Int => Int)
val f4: (Int, Int) => Int = Function.uncurried(f3) // go back to the uncurried version (type is (Int, Int) => Int)





//Classes

class MyClass(x: Int, val y: Int,
                      var z: Int):        // Defines a new type MyClass with a constructor
                                          // x will not be available outside MyClass
                                          // val will generate a getter for y
                                          // var will generate a getter and a setter for z
  require(y > 0, "y must be positive")    // precondition, triggering an IllegalArgumentException if not met
  def this (x: Int) =  ...                // auxiliary constructor
  def nb1 = x                             // public method computed every time it is called
  private def test(a: Int): Int =  ...    // private method
  val nb3 = x + y                         // computed only once
  override def toString =                 // overridden method
      x + ", " + y
end MyClass

new MyClass(1, 2, 3) // creates a new object of type

//this references the current object, assert(<condition>) issues AssertionError if condition is not met. See scala.Predef for require, assume and assert.


// End markers
/ *
* When the body of a class, object, trait, method or value becomes long, visually
* inspecting where it ends might become challenging.
* In these situations, it is possible to explicitly signal to the reader that the body
* is over using the end keyword with the name of the definition:
* /

class MyClass(a: Int, b: String):
    // body
end MyClass

object MyObject:
    // body
end MyObject

object MyTrait:
    // body
end MyTrait

def myMethod(name: String): Unit =
    println(s"Hello $name")
end myMethod

val myVal: Int =
    42
end myVal


// Operators
/ *
* myObject myMethod 1 is the same as calling myObject.myMethod(1)
* Operator (i.e. function) names can be alphanumeric, symbolic (e.g. x1, *, +?%&, vector_++, counter_=)
* The precedence of an operator is determined by its first character, with the following increasing order of priority:
* /

(all letters)
|
^
&
< >
= !
:
+ -
* / %
(all other special characters)


// Class hierarchies

abstract class TopLevel:     // abstract class
  def method1(x: Int): Int   // abstract method
  def method2(x: Int): Int =  ...
end TopLevel

class Level1 extends TopLevel:
  def method1(x: Int): Int = ...
  override def method2(x: Int): Int = ... // TopLevel's method2 needs to be explicitly overridden
end Level1

object MyObject extends TopLevel:
  ...  // defines a singleton object. No other instance can be created

//To create an runnable application in Scala:

@main def run(args: Array[String]) =
  println("Hello world")

// or

object Hello extends App:
  println("Hello World")



// Class Organization
/ *
Classes and objects are organized in packages (package myPackage).

They can be referenced through import statements (import myPackage.MyClass, import myPackage.*,
import myPackage.{MyClass1, MyClass2}, import myPackage.{MyClass1 as A})

They can also be directly referenced in the code with the fully qualified name (new myPackage.MyClass1)

All members of packages scala and java.lang as well as all members of the object scala.Predef are automatically imported.

Traits are similar to Java interfaces, except they can have non-abstract members:
* /
trait Planar:
  ...
class Square extends Shape with Planar

// Type Parameters
// Similar to C++ templates or Java generics. These can apply to classes, traits or functions.
    class MyClass[T](arg1: T):
      ...

    MyClass[Int](1)
    MyClass(1)   // the type is being inferred, i.e. determined based on the value arguments

// It is possible to restrict the type being used, e.g.

    def myFct[T <: TopLevel](arg: T): T = ... // T must derive from TopLevel or be TopLevel
    def myFct[T >: Level1](arg: T): T = ...   // T must be a supertype of Level1
    def myFct[T >: Level1 <: TopLevel](arg: T): T = ...


// Variance
// Given A <: B
// If C[A] <: C[B], C is covariant
// If C[A] >: C[B], C is contravariant
// Otherwise C is nonvariant

class C[+A]  // C is covariant
class C[-A]  // C is contravariant
class C[A]   // C is nonvariant

// For a function, if A2 <: A1 and B1 <: B2, then A1 => B1 <: A2 => B2.
// Functions must be contravariant in their argument types and covariant in their result types, e.g.

trait Function1[-T, +U]:
  def apply(x: T): U
// Variance check is OK because T is contravariant and U is covariant
class Array[+T]:
  def update(x: T) // variance checks fails

// Pattern Matching
// Pattern matching is used for decomposing data structures:
  unknownObject match
  case MyClass(n) => ...
  case MyClass2(a, b) => ...

// Here are a few example patterns

(someList: List[T]) match
  case Nil => ...          // empty list
  case x :: Nil => ...     // list with only one element
  case List(x) => ...      // same as above
  case x :: xs => ...      // a list with at least one element. x is bound to the head,
                           // xs to the tail. xs could be Nil or some other list.
  case 1 :: 2 :: cs => ... // lists that starts with 1 and then 2
  case (x, y) :: ps => ... // a list where the head element is a pair
  case _ => ...            // default case if none of the above matches

// Options

/ *
* Pattern matching can also be used for Option values. Some
* functions (like Map.get) return a value of type Option[T] which
* is either a value of type Some[T] or the value None:
* /
val myMap = Map("a" -> 42, "b" -> 43)
def getMapValue(s: String): String =
  myMap get s match
    case Some(nb) => "Value found: " + nb
    case None => "No value found"

getMapValue("a")  // "Value found: 42"
getMapValue("c")  // "No value found"

/*
Most of the times when you write a pattern match on an option value,
the same expression can be written more concisely using combinator
methods of the Option class. For example, the function getMapValue
can be written as follows:
*/
def getMapValue(s: String): String =
  myMap.get(s).map("Value found: " + _).getOrElse("No value found")

// Pattern Matching in Anonymous Functions
// Pattern matching is also used quite often in anonymous functions:
val options: List[Option[Char]] = Some('a') :: None :: Some('b') :: Nil
val chars: List[Char] = pairs.map(p => p match {
  case Some(ch) => ch
  case None => 'z'
})
// Instead of p => p match { case ... }, you can simply write { case ...}, so the above example becomes more concise:
val chars: List[Char] = pairs.map {
  case Some(ch) => ch
  case None => 'z'
}

// Collections
Scala defines several collection classes:

// Base Classes
Iterable (collections you can iterate on)https://www.scala-lang.org/api/current/scala/collection/Iterable.html

Seq (ordered sequences)

Set

Map
 (lookup data structure)

Immutable Collections
List
 (linked list, provides fast sequential access)

LazyList
 (same as List, except that the tail is evaluated only on demand)

Vector
 (array-like type, implemented as tree of blocks, provides fast random access)

Range
 (ordered sequence of integers with equal spacing)

String
 (Java type, implicitly converted to a character sequence, so you can treat every string like a Seq[Char])

Map
 (collection that maps keys to values)

Set
 (collection without duplicate elements)

Mutable Collections
Array
 (Scala arrays are native JVM arrays at runtime, therefore they are very performant)

Scala also has mutable maps and sets; these should only be used if there are performance issues with immutable types

