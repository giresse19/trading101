import { GetterTree } from 'vuex';
import { State } from './state';

export const GET_IS_FORM_SUBMITTED = 'GET_IS_FORM_SUBMITTED';
export const GET_USER = 'GET_USER';

export const getters: GetterTree<State, State> = {
    [GET_IS_FORM_SUBMITTED]: state => state.isFormSubmitted,
    [GET_USER]: state => state.user && state.user.length > 0,
};
