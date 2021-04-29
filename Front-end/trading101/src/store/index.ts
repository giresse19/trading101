import Vue from 'vue';
import Vuex from 'vuex';
import { State, state } from './state';
import { actions } from './actions';
import { mutations } from './mutations';
import { getters } from './getters';

Vue.use(Vuex);

export const makeStore = () => {
  return new Vuex.Store<State>({
    state: JSON.parse(JSON.stringify(state)),
    getters,
    mutations,
    actions
  });
};
