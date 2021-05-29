import User from '@/model/User'
import UserAddress from '../model/UserAddress'
import HeaderItem from "@/model/HeaderItem";

export interface State {
  isFormSubmitted: boolean,
  loadingStatus: boolean;
  user:  User[] | null;
  userAddress:  UserAddress[] | null;
  headerItem: string | null;
}

export const state: State = {
  isFormSubmitted: false,
  loadingStatus: false,
  user: [],
  userAddress: [],
  headerItem: null,
};
