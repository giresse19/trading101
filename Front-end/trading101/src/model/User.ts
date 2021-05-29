
import UserAddress from './UserAddress'

export default interface User {
     userId: string;
     firstName: string;
     lastName: string;
     email: string;
     createdAt: Date;
     updatedAt: Date;
    addresses: UserAddress[] | null;
}

