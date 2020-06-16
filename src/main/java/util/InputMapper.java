package util;

public interface InputMapper<T, E> {
    E mapFromInputToEntity(T toMap);

}
