
def curry[A,B,C](f: (A, B) => C): A => B => C = {
  a => b => f(a,b)
}

/*
Implement uncurry , which reverses the transformation of curry . Note that since =>
associates to the right, A => (B => C) can be written as A => B => C .
*/

def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
  (a,b) => f(a)(b)
}

/*
Implement the higher-order function that composes two functions.
*/

def compose[A,B,C](f: B => C, g: A => B): A => C = {
  a => f(g(a))
}
