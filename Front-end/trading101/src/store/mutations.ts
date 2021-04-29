import { MutationTree } from 'vuex';
import { State } from './state';

export const SET_IS_LOADING = 'SET_IS_LOADING';
export const SET_LOADING_STATUS = 'SET_LOADING_STATUS';


export const mutations: MutationTree<State> = {
   [SET_LOADING_STATUS](state, loadingStatus: string) {
    state.loadingStatus = loadingStatus;
  },
  [SET_IS_LOADING](state, isLoading: boolean) {
    state.isLoading = isLoading;
  },
};
