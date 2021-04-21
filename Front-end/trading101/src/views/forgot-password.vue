<template>
  <v-container class="grey lighten-5">
    <v-row no-gutters style="height: 1000px">
      <v-col
          cols="12"
          sm="12"
          md="12"
      >
        <div class="pa-2">
          <div class="right-body">

            <v-card
                height="25%"
                width="30%"
            >
              <v-card-title>Forgot Password</v-card-title>
              <v-card-text>
                <v-form v-model="isValid">
                  <v-text-field
                      label="Email"
                      v-model="email"
                      :rules="emailRules"
                      required
                  >

                  </v-text-field>
                </v-form>
              </v-card-text>

              <v-card-actions>
                <v-btn
                    color="red"
                    :disabled="!isValid"
                >
                  Reset Password
                </v-btn>
              </v-card-actions>
            </v-card>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {Component, Vue} from "vue-property-decorator";

@Component({
  components: {},
})

export default class ForgotPassword extends Vue {
  email = '';
  isValid = true;

  emailRules: Array<any> = [
    (v: string) => !!v || 'E-mail is required',
    (v: string) => /.+@.+/.test(v) || 'E-mail must be valid',
  ]
}
</script>
