<template>
  <v-container class="grey lighten-5">
    <v-row no-gutters style="height: 1000px">
      <v-col
          cols="10"
          sm="6"
          md="6"
      >
        <div class="pa-2" style="background-color: #B8DBE1">
          <div class="right-body">
            <v-card
                height="70%"
                width="50%"
            >
              <v-card-title>Sign Up</v-card-title>
              <v-card-text>
                <v-form v-model="isValid">
                  <v-text-field
                      label="First Name"
                      v-model="firstname"
                      :rules="[() => !!firstname || 'firstName is required']"
                      required
                  >
                  </v-text-field>
                  <v-text-field
                      label="Last Name"
                      v-model="lastname"
                      :rules="[() => !!lastname || 'lastName is required']"
                      required
                  >
                  </v-text-field>
                  <v-text-field
                      v-model="streetName"
                      label="Street Name"
                      hint="Including House No"
                      counter="25"
                      required
                      :rules="[() => !!this.streetName || 'This field is required',
                               () => !!this.streetName && this.streetName.length <= 25 || 'Street name must be less than 25 characters']"
                  ></v-text-field>
                  <v-text-field
                      v-model="city"
                      label="City"
                      required
                      :rules="[() => !!city || 'City field is required']"
                  ></v-text-field>
                  <v-text-field
                      v-model="postalCode"
                      label="ZIP / Postal Code"
                      required
                      :rules="[() => !!postalCode || 'postal code field is required']"
                  ></v-text-field>
                  <v-autocomplete
                      :filter="customFilter"
                      v-model="country"
                      item-text="name"
                      label="Country"
                      :items="countries"
                      required
                      :rules="[() => !!email || 'country is required']">
                      ></v-autocomplete>
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
                  Sign Up
                </v-btn>
              </v-card-actions>

              <p class="forgot-password text-right">
                Already registered
                <router-link :to="{name: 'login'}">Sign In?</router-link>
              </p>
            </v-card>

          </div>
        </div>
      </v-col>
      <v-col
          cols="8"
          md="6"
      >
        <div class="pa-2" style="background-color: #f3e9d2">
          <div class="right-body">
            <h3 style="color: #114b5f;">
                <span class="right-text">
               Welcome to  <span style="font-family: 'Rage Italic',serif; font-weight: bolder">TRADING101,</span> signup and get the most exciting trading <br> experience driven by our
          advance predictive trading algorithms.
                   </span>

            </h3>
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
export default class SignUp extends Vue {

  firstname = '';
  lastname = '';
  email = '';
  city='';
  password = '';
  streetName='';
  postalCode='';
  country='';
  isValid = true;
  countries:Array<any> = [
    { name: 'Estonia', abbr: 'EE', id: 1 },
    { name: 'Georgia', abbr: 'GA', id: 2 },
    { name: 'United States', abbr: 'US', id: 3 },
    { name: 'Cameroon', abbr: 'CA', id: 4 },
    { name: 'Germany', abbr: 'NY', id: 5 },
    { name: 'Sweden', abbr: 'SV', id: 6 },
  ];

  customFilter (item:any, queryText:string, itemText:any) {
    const textOne = item.name.toLowerCase()
    const textTwo = item.abbr.toLowerCase()
    const searchText = queryText.toLowerCase()

    return textOne.indexOf(searchText) > -1 ||
        textTwo.indexOf(searchText) > -1
  };
}
</script>
<style scoped lang="scss">

.right-text {
  font-weight: bold;
  font-family: 'Antonio', sans-serif;
  text-align: center;
  padding: 4px;
}



</style>