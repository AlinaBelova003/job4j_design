package ru.job4j.generics;

public class UserStore implements Store<User> {
    /**
     * Создаём хранилище по интерфейсу Store cодержащие поля User,
     * вызываем реализацию этих методов из MemStore, поскольку объект именно этого типа мы используем в качестве хранилища.
     */
    private final Store<User> store = new MemStore<>();

    /**
     * вызываем реализации методов, заного их не пишем.
     */
    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
