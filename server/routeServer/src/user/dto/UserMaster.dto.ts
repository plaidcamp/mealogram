import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm'

/* 
 * 
 *
 */
@Entity({ name: "user_master" })
export class UserMaster {

    @PrimaryGeneratedColumn()
    id: String; // Hash값으로 각 계정을 Unique하게 관리

    @Column({ nullable: false })
    gender: string; // M / F

    @Column({ type: "varchar", length: 100 })
    name: string;
}