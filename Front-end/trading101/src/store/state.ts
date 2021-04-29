

export interface State {
  isLoading: boolean;
  loadingStatus: string | null;
}

export const state: State = {
  isLoading: false,
  loadingStatus: null
};
