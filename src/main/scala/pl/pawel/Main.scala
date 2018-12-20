package pl.pawel

object Main extends App {
  val tree = MerkelTree.build(List())
  println(tree.rootHash)
}
