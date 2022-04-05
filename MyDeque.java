import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDeque<T> implements Iterable<T> {
	private class Node<T> {
		private T content;
		private Node<T> prev;
		private Node<T> next;

		public Node(T content, Node<T> prev, Node<T> next) {
			this.content = content;
			this.prev = prev;
			this.next = next;
		}

		public T getContent() {
			return content;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public Node<T> getNext() {
			return next;
		}
	}

	private Node<T> head;
	private Node<T> tail;

	public MyDeque() {
		head = null;
		tail = null;
	}

	public void addFirst(T content) {
		Node<T> new_node = new Node<>(content, null, head);

		head = new_node;
		if (tail == null)
			tail = new_node;
		if (head.getNext() != null)
			head.getNext().setPrev(new_node);
	}

	public void addLast(T content) {
		Node<T> new_node = new Node<>(content, tail, null);

		tail = new_node;
		if (head == null)
			head = new_node;
		if (head.getPrev() != null)
			head.getPrev().setNext(new_node);
	}

	public void insertAfter(Node<T> prev_node, T content) {
		if (prev_node == null)
			return;

		Node<T> new_node = new Node<>(content, prev_node, prev_node.next);
		
		prev_node.setNext(new_node);
		if (new_node.getNext() != null)
			new_node.getNext().setPrev(new_node);
	}

	public void insertBefore(Node<T> next_node, T content) {
		if (next_node == null)
			return;

		Node<T> new_node = new Node<>(content, next_node.getPrev(), next_node);

		next_node.setPrev(new_node);
		if (new_node.getPrev() != null)
			new_node.getPrev().setNext(new_node);
	}

	public T removeFirst() {
		T content = head.getContent();
		head = head.getNext();
		head.setPrev(null);
		return content;
	}

	public T removeLast() {
		T content = tail.getContent();
		tail = tail.getPrev();
		tail.setNext(null);
		return content;
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> ret = new ArrayList<>();
		for (Node<T> pointer = head; pointer != null; pointer = pointer.next)
			ret.add(pointer.getContent());
		return ret;
	}

	public ArrayList<T> toReverseArrayList() {
		ArrayList<T> ret = new ArrayList<>();
		for (Node<T> pointer = tail; pointer != null; pointer = pointer.prev)
			ret.add(pointer.getContent());
		return ret;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> pointer = head;
			
			public boolean hasNext() {
				return pointer != null;
			}

			public T next() {
				T ret = pointer.content;
				pointer = pointer.next;
				return ret;
			}
		};
	}

	public static void main(String[] args) {
		MyDeque<Integer> dll = new MyDeque<>();
		dll.addFirst(4);
		dll.addFirst(3);
		dll.addFirst(7);
		dll.insertAfter(dll.head, 9);
		System.out.println(dll.toArrayList());
		System.out.println(dll.toReverseArrayList());
		for (int i : dll) {
			System.out.println(i);
		}
		System.out.println(dll.removeFirst());
		System.out.println(dll.removeLast());
	}
}
