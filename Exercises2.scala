// 2.3 Curry
def curry[A,B,C] (f: (A, B) => C): A => (B => C) =
    a => b => f(a, b)

def f(a: Int, b: Int): Int = a + b
def g(a: Int)(b: Int): Int = a + b

curry(f)(1)(1) == f(1, 1) shouldBe 2
curry(f)(1)(1) == g(1)(1) shouldBe 2

// 2.4 uncurry
def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a, b) => f(a)(b)

def f(a: Int, b: Int): Int = a + b
def g(a: Int)(b: Int): Int = a + b

uncurry(g)(1, 1) == g(1)(1) shouldBe 2

uncurry(g)(1, 1) == f(1, 1) shouldBe 2

// 2.5 Compose
def compose[A,B,C](f: B => C, g: A => B): A => C
    a => f(g(a))

def f(b: Int): Int = b / 2
def g(a: Int): Int = a + 2

compose(f, g)(0) == compose(g, f)(0) shouldBe 
compose(f, g)(2) shouldBe 
compose(g, f)(2) shouldBe 
