import { ActionTree } from 'vuex';
import { State } from './state';
import {
  SET_LOADING_STATUS,
  SET_IS_LOADING
} from './mutations';



// const refreshContactsData = () => {
//
// };

export const actions: ActionTree<State, State> = {
  // async load({ state, commit }) {
  //   commit("", true);
  //
  //   try {
  //   //  const data = await permanentStoreHelper.load(state.config.portletElementId);
  //
  //   } catch (err) {
  //
  //   }
  //   commit(SET_IS_LOADING, false);
  // },

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
