package pl.pawel

final case class MerkelNode(hash: String, left: Option[MerkelNode] = None, right: Option[MerkelNode] = None) {
  def +(merkelNode: MerkelNode): MerkelNode = MerkelNode(hash + merkelNode.hash, Some(this), Some(merkelNode))
}