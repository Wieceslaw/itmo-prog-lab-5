package elements;

import exceptions.WrongArgumentsException;

/**
 * Класс с адресом Organisation
 *
 * @author Лебедев Вячеслав
 */
public class Address {
    private String street; //Строка не может быть пустой, Поле может быть null
    private String zipCode; //Поле может быть null

    /**
     * @param street улица
     * @param zipCode зип код
     */
    public Address(String street, String zipCode) throws WrongArgumentsException {
        setStreet(street);
        setZipCode(zipCode);
    }

    /**
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Адрес " +
                "улица " + street +
                ", зип код " + zipCode;
    }


    /**
     * @param street параметр сеттера улицы
     */
    public void setStreet(String street) throws WrongArgumentsException {
        if (street == null || !street.isEmpty()) {
            this.street = street;
        } else {
            throw new WrongArgumentsException("street");
        }
    }

    /**
     * @return улица
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param zipCode параметр сеттера зап кода
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return зип код
     */
    public String getZipCode() {
        return zipCode;
    }
}