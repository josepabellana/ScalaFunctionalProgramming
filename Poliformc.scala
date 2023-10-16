def findFirst(ss: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
        if (n >= ss.length) -1 
        else if (ss(n) == key) n 
        else loop(n + 1)
    loop(0)
}

// inner call no stack created
def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
        if (n >= as.length) -1 
        else if (p(as(n))) n 
        else loop(n + 1)
    loop(0)
}
def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean =
        if (n+1 > as.length) True
        else if (!ordered(as(n), as(n+1))) False
        else loop(n+1)
    loop(0)
}