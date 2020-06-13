package util;

public interface Mapper<I, E> {
    abstract E mapFromInputToEntity(I toMap);

}
