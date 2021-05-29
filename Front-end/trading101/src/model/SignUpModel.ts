import UserAddress from './UserAddress'

export default interface SignUpModel {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    addresses: UserAddress[] | null;

}