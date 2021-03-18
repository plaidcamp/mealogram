import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm'

@Entity()
/* 
 * 
 *
 */
export class UserMaster {
    
    @PrimaryGeneratedColumn()
    id: String; // Hash값으로 각 계정을 Unique하게 관리

    @Column()
    gender: string; // M / F

    @Column()
    name: string;

    @Column()

}