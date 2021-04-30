import { ActionTree } from 'vuex';
import { State } from './state';
import {
  SET_LOADING_STATUS,
  SET_IS_LOADING,
  SET_IS_FORM_SUBMITTED
} from './mutations';

export const setFormSubmissionStatusToTrue = 'setFormSubmissionStatusToTrue';
export const setFormSubmissionStatusToFalse = 'setFormSubmissionStatusToFalse';

export const actions: ActionTree<State, State> = {
  async setFormSubmissionStatusToTrue({ state, commit }) {
    if(!state.isFormSubmitted){
      commit(SET_IS_FORM_SUBMITTED, true);
    }
  },
  async setFormSubmissionStatusToFalse({ state, commit }) {
    if(state.isFormSubmitted){
      commit(SET_IS_FORM_SUBMITTED, false);
    }
  },

  // async save ({ state, commit }) {
  //   commit(SET_IS_LOADING, true);
  //
  //   try {
  //
  //   } catch (err) {
  //     commit(SET_LOADING_STATUS, err);
  //   }
  //
  //   commit(SET_IS_LOADING, false);
  // }
};
