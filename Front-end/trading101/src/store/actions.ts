import {ActionTree} from 'vuex';
import {State} from './state';
import {SET_IS_FORM_SUBMITTED, SET_LOADING_STATUS, SET_USER} from './mutations';
import config from "@/config/config";
import {displayMessageError} from "@/utils/requestHelpers";

export const setFormSubmissionStatusToTrue = 'setFormSubmissionStatusToTrue';
export const setFormSubmissionStatusToFalse = 'setFormSubmissionStatusToFalse';
export const creatAccount = 'creatAccount';
export const login = 'login';
export const setUser = 'setUser';
export const fetchUserInfo = 'fetchUserInfo';

export const actions: ActionTree<State, State> = {
  async [creatAccount] ({ state, commit }, fetchOptionPost: any) {

    const response = await fetch(`${config.BASE_URL}`, fetchOptionPost);
    commit(SET_LOADING_STATUS, true);

    if (response.ok) {
      commit(SET_LOADING_STATUS, false);
      commit(SET_IS_FORM_SUBMITTED, true);
    } else {
       await displayMessageError("Error while creating account");
       commit(SET_IS_FORM_SUBMITTED, false)
    }
  },

  async [login]({ commit, dispatch }, fetchOptionPost: any) {
    const response = await fetch(`${config.BASE_URL}/${config.LOGIN}`, fetchOptionPost);
    commit(SET_LOADING_STATUS, true);

    if (response.ok) {
      dispatch('fetchUserInfo', { response: response });
    } else {
      console.log("response", response)
      await displayMessageError("Error while login into account");
    }
    commit(SET_LOADING_STATUS, false);
  },

  async [fetchUserInfo]({ commit }, response: any) {
    const userDetails = await fetch(`${config.BASE_URL}/${response.headers.get(config.USERID)}`,
        config.fetchOptionsGet(response.headers.get(config.AUTHORIZATION_HEADER))
    );

      if (userDetails.ok) {
        console.log("user details", userDetails);
        commit(SET_USER, userDetails);
      } else {
        console.log("response: User not found");
        await displayMessageError("Error while fetching user details");
      }
  },
};
