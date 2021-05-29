import { MutationTree } from 'vuex';
import { State } from './state';
import {creatAccount} from "@/store/actions";
import User from "@/model/User";

export const SET_LOADING_STATUS = 'SET_LOADING_STATUS';
export const SET_IS_FORM_SUBMITTED = 'SET_IS_FORM_SUBMITTED ';
export const SET_USER = 'SET_USER ';


export const mutations: MutationTree<State> = {
   [SET_LOADING_STATUS](state, loadingStatus: boolean) {
    state.loadingStatus = loadingStatus;
  },
  [SET_IS_FORM_SUBMITTED](state, isFormSubmitted: boolean) {
    state.isFormSubmitted = isFormSubmitted;
  },
    [SET_USER](state, user: User[]) {
        state.user = user;
    },

};
