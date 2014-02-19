package prototype;

//user would need to implement this interface to customize their own distance measurement


interface DistanceMeasure<T> {
		abstract public double measure(T x, T y);
}
