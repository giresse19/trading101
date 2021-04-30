

export interface State {
  isFormSubmitted: boolean,
  isLoading: boolean;
  loadingStatus: string | null;
}

export const state: State = {
  isFormSubmitted: false,
  isLoading: false,
  loadingStatus: null
};
