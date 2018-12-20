package pl.pawel

class MerkelTree[+A] private(val leafs: List[A] = List(), val rootHash: String = "") extends Hash[A] {

  def build[B >: A](list: List[B]): MerkelTree[B] = {

    def buildTree(lowerLayer: List[MerkelNode],
                  higherLayer: List[MerkelNode] = List()): MerkelTree[B] = {
      lowerLayer match {
        case root :: Nil if higherLayer.isEmpty =>
          new MerkelTree[B](list, root.hash)
        case node :: Nil =>
          buildTree(higherLayer ::: List(node), List())
        case node1 :: node2 :: Nil =>
          buildTree(higherLayer ::: List(node1 + node2), List()) //todo should be also computed hash
        case node1 :: node2 :: tail =>
          buildTree(tail, higherLayer ::: List(node1 + node2))
      }
    }

    def createNodesFromLeafs(leafs: List[B], merkelNode: List[MerkelNode]): List[MerkelNode] = {
      leafs match {
        case head :: Nil =>
          merkelNode ::: List(MerkelNode(compute(head)))
        case head :: tail =>
          createNodesFromLeafs(tail, merkelNode ::: List(MerkelNode(compute(head))))
      }
    }

    if (list.isEmpty) {
      MerkelTree.empty[B]
    } else {
      buildTree(createNodesFromLeafs(list, List()))
    }
  }

  def append[B >: A](element: B) = leafs ::: List(element)

  def verify(hashString: String): Boolean = ???

  override def compute[B >: A](element: B): String = element.toString
}

object MerkelTree {
  def empty[A]: MerkelTree[A] = new MerkelTree

  def build[A](leafs: List[A]): MerkelTree[A] = new MerkelTree[A]().build(leafs)
}