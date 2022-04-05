package processors;

import elements.Organization;

import java.util.*;

/**
 * Класс-обертка для работы с коллекцией
 */

public class CollectionProcessor {
    private final HashSet<Organization> collection;
    private final Date createDate;
    private final HashSet<Long> idSet;

    /**
     * Конструктор класса
     */
    public CollectionProcessor() {
        this.collection = new HashSet<>();
        this.createDate = new Date();
        this.idSet = new HashSet<>();
    }

    /**
     * @param element элемент класса Organisation, который надо добавить в коллекцию, может содержать ID, или не содержать
     */
    public void add(Organization element) {
        // Добавить проверку при добавлении элемента с заданным ID, чтобы такого ID не было
        Long id = element.getId();
        if (id == null) {
            Random random = new Random();
            do {
                id = Math.abs(random.nextLong());
            } while (this.idSet.contains(id));
            element.setId(id);
        }
        this.idSet.add(id);
        collection.add(element);
    }

    /**
     *
     * @return collection объект коллекции, с которым ведется работа
     */
    public HashSet<Organization> getCollection() {
        return collection;
    }

    /**
     *
     * @return createDate дата создания обработчика и коллекции
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     * @return size число элементов коллекции
     */
    public int getSize() {
        return collection.size();
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        this.collection.clear();
        this.idSet.clear();
    }

    /**
     *
     * @param id элемента, по которому ведется поиск
     * @return элемент коллекции с переданным {@code id}, {@code null}, если элемент не найден
     */
    public Organization getElementById(Long id) {
        for (Organization organization : this.collection) {
            if (Objects.equals(organization.getId(), id)) {
                return organization;
            }
        }
        return null;
    }

    /**
     *
     * @return отсортированный список с элементами коллекции
     */
    public List<Organization> getSortedList() {
        List<Organization> list = new ArrayList<>(this.collection);
        Collections.sort(list);
        return list;
    }

    /**
     *
     * @return минимальный элемент коллекции, {@code null}, если элемент не найден
     */
    public Organization getMin() {
        if (!this.isEmpty()) {
            return this.getSortedList().get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @return максимальный элемент коллекции, {@code null}, если элемент не найден
     */
    public Organization getMax() {
        if (!this.isEmpty()) {
            return this.getSortedList().get(this.getSize() -1);
        } else {
            return null;
        }
    }

    /**
     *
     * @return {@code true} если коллекция не содержит элементов
     */
    public boolean isEmpty() {
        return this.collection.isEmpty();
    }

    /**
     *
     * @param element удаляемый элемент из коллекции
     */
    public void remove(Organization element) {
        this.idSet.remove(element.getId());
        this.collection.remove(element);
    }
}