<template>
  <v-container class="grey lighten-5">
 <span v-if="$store.state.isFormSubmitted">
<TheNotification text='Account created, redirected to Login Page!'>
</TheNotification>
 </span>
    <v-row no-gutters style="height: 1000px">

      <v-col
          cols="12"
          sm="12"
          md="12"
      >
        <div class="pa-2">
          <div class="right-body">
            <v-card
                height="40%"
                width="30%"
                background=""
            >
              <v-card-title>Sign In</v-card-title>
              <v-card-text>
                <v-form v-model="isValid">
                  <v-text-field
                      label="Email"
                      v-model="email"
                      required
                      :rules="[
                          () => !!email || 'E-mail is required',
                          () => /.+@.+/.test(email) || 'E-mail must be valid']"
                  >
                  </v-text-field>
                  <v-text-field
                      label="Password"
                      v-model="password"
                      :rules="[
                          () => !!password || 'password field is required',
                          () => (password && password.length > 5),
                          () => /(?=.*[A-Z])/.test(password) || 'Must have one uppercase character',
                          () => /(?=.*\d)/.test(password) || 'Must have one number',
                          () => /([!@$%])/.test(password) || 'Must have one special character [!@#$%]']"
                      type="password"
                      required
                  >
                  </v-text-field>
                </v-form>
              </v-card-text>

              <v-card-actions>
                <v-btn
                    class="btn-style"
                    :disabled="!isValid">
                  Login
                </v-btn>
              </v-card-actions>

              <p class="forgot-password text-right mt-2 mb-4">
                <router-link to="/forgot-password">Forgot password ?</router-link>
              </p>
              <div class="social-icons">
                <ul class="socials">
                  <li>
                    <v-btn icon tile id="facebook">
                      <v-icon large color="blue">mdi-facebook</v-icon>
                    </v-btn>
                  </li>
                  <li>
                    <v-btn icon tile id="google">
                      <v-icon large color="red">mdi-google</v-icon>
                    </v-btn>
                  </li>
                  <li>
                    <v-btn icon tile id="reddit">
                      <v-icon large color="deep-orange">mdi-reddit</v-icon>
                    </v-btn>
                  </li>
                </ul>
              </div>
            </v-card>
          </div>
        </div>
      </v-col>
    </v-row>

  </v-container>
</template>

<script lang="ts">
import {Component, Vue} from "vue-property-decorator";
import TheNotification from "@/views/TheNotification.vue";

@Component({
  components: {TheNotification},
})

export default class Login extends Vue {
  email = '';
  password = '';
  isValid = true;

}
</script>

<style scoped lang="scss">
#facebook {
  color: blue;
}

#google {
  color: mediumpurple;
}

#reddit {
  color: red;
}
</style>
