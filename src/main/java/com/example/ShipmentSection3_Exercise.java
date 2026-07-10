package com.example;

import java.util.ArrayList;
import java.util.List;

// ╔══════════════════════════════════════════════════════════╗
//  SECTION 3 — แบบฝึกหัด (Exercise)
//  ชื่อนักศึกษา : ___________________________
//  รหัสนักศึกษา : ___________________________
// ╚══════════════════════════════════════════════════════════╝
//
//  โจทย์:
//    บริษัท RocketShip Thailand มีรายการ Shipment หลายรายการ
//    ให้คำนวณค่าขนส่งตามน้ำหนักและประเภท แล้วแสดงยอดรวม
//
//  กฎการคำนวณ:
//    ประเภท STANDARD  →   40 บาท / กิโลกรัม
//    ประเภท EXPRESS   →  100 บาท / กิโลกรัม
//
//  คำสั่ง:
//    หา Bug และเติม code ในทุกจุดที่มี 👉 TODO
//    แล้วรันให้ได้ผลลัพธ์ตาม ExpectedOutput_Section3.md
// ══════════════════════════════════════════════════════════

// ──────────────────────────────────────────────────────────
//  PART A : Enum ประเภทการขนส่ง
// ──────────────────────────────────────────────────────────
// ✅ ส่วนนี้ถูกต้องแล้ว ไม่ต้องแก้
enum ShipmentType {
    STANDARD,
    EXPRESS
}

// ──────────────────────────────────────────────────────────
//  PART B : Class Shipment — ข้อมูลพัสดุแต่ละรายการ
// ──────────────────────────────────────────────────────────
class Shipment {

    private String       trackingNumber;
    private double       weightKg;
    private ShipmentType type;

    // ✅ Constructor ถูกต้องแล้ว ไม่ต้องแก้
    public Shipment(String trackingNumber, double weightKg, ShipmentType type) {
        this.trackingNumber = trackingNumber;
        this.weightKg       = weightKg;
        this.type           = type;
    }

    public String       getTrackingNumber() { return trackingNumber; }
    public double       getWeightKg()       { return weightKg;       }
    public ShipmentType getType()           { return type;           }

    // 👉 TODO A : แก้ไข if condition สลับกันเรียบร้อยแล้ว
    public double calculateCost() {
        final double STANDARD_RATE =  40.0;
        final double EXPRESS_RATE  = 100.0;
        if (type == ShipmentType.STANDARD) {          // ← แก้จาก EXPRESS เป็น STANDARD
            return weightKg * STANDARD_RATE;
        } else {
            return weightKg * EXPRESS_RATE;
        }
    }

    // 👉 TODO B : เติม String.format() แสดงผลลัพธ์ให้ตรงตามรูปแบบรีพอร์ต
    @Override
    public String toString() {
        return String.format("[%s] %6.2f กก. | %-8s | %9.2f บาท", 
                trackingNumber, weightKg, type, calculateCost());
    }
}

// ──────────────────────────────────────────────────────────
//  PART C : Class ShippingCompany — บริษัทขนส่ง
// ──────────────────────────────────────────────────────────
class ShippingCompany {

    private String         name;
    private List<Shipment> shipments;

    // ✅ initialize ถูกต้องแล้ว ไม่ต้องแก้
    public ShippingCompany(String name) {
        this.name      = name;
        this.shipments = new ArrayList<>();
    }

    public void addShipment(Shipment s) {
        shipments.add(s);
    }

    // 👉 TODO C : แก้ไขให้วนลูปดึงข้อมูลด้วยดัชนี get(i) แทนตัวแรก get(0)
    public double getTotalCost() {
        double total = 0;
        for (int i = 0; i < shipments.size(); i++) {
            total += shipments.get(i).calculateCost();  // ← แก้จาก get(0) เป็น get(i)
        }
        return total;
    }

    // 👉 TODO D : เติมลูปแสดงรายการพัสดุ และบรรทัดแสดงยอดรวมทั้งหมด
    public void printSummary() {
        System.out.println("========================================");
        System.out.printf ("  บริษัท        : %s%n",   name);
        System.out.printf ("  จำนวน Shipment : %d รายการ%n", shipments.size());
        System.out.println("========================================");

        // 1) วนลูปแสดงแต่ละ shipment 
        for (Shipment s : shipments) {
            System.out.println(s.toString());
        }

        System.out.println("----------------------------------------");
        // 2) แสดงยอดรวม
        System.out.printf ("  ยอดรวมค่าขนส่ง : %9.2f บาท%n", getTotalCost());
    }
}

// ──────────────────────────────────────────────────────────
//  PART D : Main
// ──────────────────────────────────────────────────────────
public class ShipmentSection3_Exercise {
    public static void main(String[] args) {

        ShippingCompany company = new ShippingCompany("RocketShip Thailand");

        // (trackingNumber, weightKg, type)
        company.addShipment(new Shipment("RS001",  2.0,  ShipmentType.STANDARD));
        company.addShipment(new Shipment("RS002",  3.5,  ShipmentType.EXPRESS));
        company.addShipment(new Shipment("RS003",  7.0,  ShipmentType.STANDARD));
        company.addShipment(new Shipment("RS004",  0.5,  ShipmentType.EXPRESS));
        company.addShipment(new Shipment("RS005", 12.0,  ShipmentType.STANDARD));

        company.printSummary();
    }
}