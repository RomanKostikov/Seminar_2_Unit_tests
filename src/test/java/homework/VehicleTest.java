package homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehicleTest {

    Car car = new Car("Nissan", "Qashqai", 2022);
    Motorcycle moto = new Motorcycle("Ducati", "Monster", 2021);

    //- �������� ����, ��� ��������� ������� Car ����� �������� ����������� ������������� ��������; (instanceof)
    @Test
    void instanceOf() {
        //Car car = new Car("Nissan", "Qashqai", 2022);
        assertThat(car instanceof Vehicle);
    }

    //- �������� ����, ������ Car ��������� � 4-�� ��������
    @Test
    void WheelsCar() {
        //Car car = new Car("Nissan", "Qashqai", 2022);
        assertThat(car.getNumWheels()).isEqualTo(4);
    }

    // - �������� ����, ������ Motorcycle ��������� � 2-�� ��������
    @Test
    void MotorcycleWheels() {
        //Motorcycle moto = new Motorcycle("Ducati", "Monster", 2021);
        assertThat(moto.getNumWheels()).isEqualTo(2);
    }

    //- �������� ����, ������ Car ��������� �������� 60 � ������ ��������� �������� (testDrive())
    @Test
    void testDriveCar() {
        car.testDrive();
        assertThat(car.getSpeed()).isEqualTo(60);
    }

    // - �������� ����, ������ Motorcycle ��������� �������� 75 � ������ ��������� �������� (testDrive())
    @Test
    void testDriveMotorcycle() {
        moto.testDrive();
        assertThat(moto.getSpeed()).isEqualTo(75);
    }

    //- ���������, ��� � ������ �������� (������� testDrive, ����� park,
    // �.� �������� �������� ����������) ������ ��������������� (speed = 0)
    @Test
    void parkCar() {
        car.testDrive();
        //System.out.println(car.getSpeed());
        car.park();
        assertThat(car.getSpeed()).isEqualTo(0);
    }

    //- ���������, ��� � ������ �������� (������� testDrive, ����� park
    // �.� �������� �������� ����������) �������� ��������������� (speed = 0)
    @Test
    void parkMotorcycle() {
        moto.testDrive();
        //System.out.println(moto.getSpeed());
        moto.park();
        assertThat(moto.getSpeed()).isEqualTo(0);
    }

}