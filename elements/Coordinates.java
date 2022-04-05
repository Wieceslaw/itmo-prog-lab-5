package elements;

import exceptions.WrongArgumentsException;

/**
 * Класс с координатами Organisation
 *
 * @author Лебедев Вячеслав
 */
public class Coordinates {
    private Double x; //Максимальное значение поля: 76, Поле не может быть null
    private Float y; //Поле не может быть null

    /**
     * Конструктор
     *
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(Double x, Float y) throws WrongArgumentsException {
        setX(x);
        setY(y);
    }
//
//    /**
//     * @return строковое представление объекта
//     */
//    @Override
//    public String toString() {
//        return "Coordinates{" +
//                "x=" + x +
//                ", y=" + y +
//                '}';
//    }


    /**
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * @return координату x
     */
    public Double getX() {
        return x;
    }

    /**
     * @param x параметр сеттера координаты x
     */
    public void setX(Double x) throws WrongArgumentsException {
        if (x != null && x <= 76) {
            this.x = x;
        } else {
            throw new WrongArgumentsException();
        }
    }

    /**
     *
     * @return координату y
     */
    public Float getY() {
        return y;
    }

    /**
     * @param y параметр сеттера координаты y
     */
    public void setY(Float y) throws WrongArgumentsException {
        if (y != null) {
            this.y = y;
        } else {
            throw new WrongArgumentsException();
        }
    }
}
