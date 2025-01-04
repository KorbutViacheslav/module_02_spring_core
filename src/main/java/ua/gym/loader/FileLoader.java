package ua.gym.loader;

import java.util.Map;

public interface FileLoader<T> {

    Map<Long, T> load();
}
