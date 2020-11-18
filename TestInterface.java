package _20201102;

import java.util.Arrays;
interface USB {
    public void turnOn();
    public void turnOff();
}
class mouse implements USB {
    @Override
    public void turnOn() {
        System.out.println("mouse is on");
    }
    @Override
    public void turnOff() {
        System.out.println("mouse is off");
    }
}
class keyBoard implements USB {
    @Override
    public void turnOn() {
        System.out.println("keyBoard is on");
    }
    @Override
    public void turnOff() {
        System.out.println("keyBoard is off");
    }
}
class UsbEar implements USB {
    @Override
    public void turnOn() {
        System.out.println("UsbEar is on");
    }
    @Override
    public void turnOff() {
        System.out.println("UsbEar is off");
    }
}
class ZigBee implements USB{
    @Override
    public void turnOn() {
        System.out.println("ZigBee is on");
    }
    @Override
    public void turnOff() {
        System.out.println("ZigBee is off");
    }
}
class BlueTooth implements USB {
    @Override
    public void turnOn() {
        System.out.println("BlueTooth is on");
    }
    @Override
    public void turnOff() {
        System.out.println("BlueTooth is off");
    }
}
class Gsm implements USB {
    @Override
    public void turnOn() {
        System.out.println("Gsm is on");
    }
    @Override
    public void turnOff() {
        System.out.println("Gsm is off");
    }
}
class Lab {
    public USB[] usb = new USB[6];
    int length = usb.length;
    public void add (int index,USB u) {
        if (isFull()) {
            usb = Arrays.copyOf(usb,2*length);
        }
        if (index < 0 || index > length) {
            System.out.println("index插入的usb位置不合法");
            return;
        }
        for (int i = length - 1; i >= index; i--) {
            usb[i+1] = usb[i];
        }
        usb[index] = u;
        length++;
    }

    private boolean isFull() {
        if (usb.length == 6) {
            return true;
        }
        return false;
    }

    public void decrease(USB u1) {
        int index = search(u1);
        if (index == -1) {
            System.out.println("没有要删除的Usb");
                }
        for (int j = index; j < usb.length - 1; j++) {
            usb[j] = usb[j+1];
        }
        length--;
    }

    public int search(USB u1) {
        for (int i = 0; i < usb.length; i++) {
            if (usb[i].equals(u1)) {
                return i;
            }
        }
        return -1;
    }
    public void turnOn() {
        System.out.println("lab is on");
        for (int i = 0; i < usb.length; i++) {
            if (usb[i] != null) {
                usb[i].turnOn();
            }
        }
        System.out.println("all devices in lab is on");
    }
    public void turnOff() {
        System.out.println("lab is off");
        for (int i = 0; i < usb.length; i++) {
            if (usb[i] != null) {
                usb[i].turnOff();
            }
        }
        System.out.println("all devices in lab is off");
    }

}
public class TestHomework1 {
    public static void main(String[] args) {
        Lab lab = new Lab();
        mouse u1 = new mouse();
        keyBoard u2 = new keyBoard();
        UsbEar u3 = new UsbEar();
        ZigBee u4 = new ZigBee();
        Gsm u5 = new Gsm();
        BlueTooth u6 = new BlueTooth();
        lab.add(0,u1);
        lab.add(1,u2);
        lab.add(2,u3);
        lab.add(3,u4);
        lab.add(4,u5);
        lab.add(5,u6);
        lab.turnOn();
        System.out.println("调试删除USB");
        lab.decrease(u1);
        lab.turnOff();
    }
}
