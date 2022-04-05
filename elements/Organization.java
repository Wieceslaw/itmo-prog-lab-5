package elements;

import exceptions.WrongArgumentsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс Organisation - элементы коллекции
 *
 * @author Лебедев Вячеслав
 */
public class Organization implements Comparable<Organization> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private int employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null

    public Organization(Long id, String name, Coordinates coordinates, Integer annualTurnover, int employeesCount, OrganizationType type, Address officialAddress) throws WrongArgumentsException {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = LocalDateTime.now();
        setAnnualTurnover(annualTurnover);
        setEmployeesCount(employeesCount);
        setType(type);
        setOfficialAddress(officialAddress);
    }

    public Organization(String name, Coordinates coordinates, Integer annualTurnover, int employeesCount, OrganizationType type, Address officialAddress) throws WrongArgumentsException {
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = LocalDateTime.now();
        setAnnualTurnover(annualTurnover);
        setEmployeesCount(employeesCount);
        setType(type);
        setOfficialAddress(officialAddress);
    }

    @Override
    public String toString() {
        return "Организация " +
                "(" + id + ") " + name +
                ", координаты " + coordinates +
                ", создана " + creationDate.format(DateTimeFormatter.ofPattern("dd/M/yyyy hh:mm:ss")) +
                " c годовым оборотом " + (annualTurnover == null ? "(отсутствует)" : annualTurnover)+
                " и числом сотрудников " + employeesCount +
                ", тип " + (type == null ? "(отсутствует)" : type) +
                ", расположенная по адресу: улица " +
                (officialAddress.getStreet() == null ? "(отсутствует)" : officialAddress.getStreet()) + ", " +
                (officialAddress.getZipCode() == null ? "(отсутствует)" : officialAddress.getZipCode());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws WrongArgumentsException {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new WrongArgumentsException();
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws WrongArgumentsException {
        if (coordinates != null) {
            this.coordinates = coordinates;
        } else {
            throw new WrongArgumentsException();
        }
    }

    public Integer getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Integer annualTurnover) throws WrongArgumentsException {
        if (annualTurnover == null || annualTurnover > 0) {
            this.annualTurnover = annualTurnover;
        } else {
            throw new WrongArgumentsException();
        }
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) throws WrongArgumentsException {
        if (employeesCount > 0) {
            this.employeesCount = employeesCount;
        } else {
            throw new WrongArgumentsException();
        }
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    @Override
    public int compareTo(Organization o) {
        return this.getEmployeesCount() - o.getEmployeesCount();
    }
}
