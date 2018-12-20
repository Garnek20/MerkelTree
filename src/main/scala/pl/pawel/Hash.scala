package pl.pawel

trait Hash[+A] {
  def compute[B >: A](element: B): String
}
