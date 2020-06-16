package util;

import javax.ejb.Local;

public interface Mapper<T, E> {
    abstract E mapFromInputToEntity(T toMap);

}
