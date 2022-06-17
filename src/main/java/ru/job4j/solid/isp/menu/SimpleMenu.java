package ru.job4j.solid.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean res = false;
        if (findItem(childName).isPresent()) {
            res = false;
        }
        Objects.equals(SimpleMenu.ROOT, parentName);
        if (findItem(parentName).isPresent()) {
            rootElements.add(new SimpleMenuItem(parentName, actionDelegate));
            res = true;
        }
        return res;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(i -> new MenuItemInfo(i.menuItem, i.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfsIterator = new DFSIterator();
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                /*
                    return dfsIterator.next(); exception
                 */
                return null;
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        Optional<ItemInfo> res = Optional.empty();
        for (ItemInfo i = iterator.next(); iterator.hasNext(); iterator.next()) {
            if (Objects.equals(name, i.menuItem.getName())) {
                res = Optional.of(i);
                break;
            }
        }
        return res;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}
