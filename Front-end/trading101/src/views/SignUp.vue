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
                height="80%"
                width="50%"
            >
              <v-card-title>Sign Up</v-card-title>
              <v-card-text>
                <v-form v-model="isValid">
                  <v-text-field
                      ref="firstName"
                      label="First Name"
                      v-model="firstname"
                      :rules="() => !!firstname || 'firstName is required'"
                      required
                      solo-inverted
                      background-color="red lighten-2"
                  >
                  </v-text-field>
                  <v-text-field
                      ref="lastName"
                      label="Last Name"
                      v-model="lastname"
                      :rules="() => !!lastname || 'lastName is required'"
                      required
                      solo-inverted
                      background-color="red lighten-2"
                  >
                  </v-text-field>
                  <v-text-field
                      ref="streetName"
                      :rules="addressRule"
                      v-model="streetName"
                      label="Street Name"
                      hint="Including House No"
                      counter="25"
                      required
                      solo-inverted
                      background-color="red lighten-2"
                  ></v-text-field>
                  <v-text-field
                      ref="city"
                      :rules="[() => !!city || 'This field is required']"
                      v-model="city"
                      label="City"
                      required
                      solo-inverted
                      background-color="red lighten-2"
                  ></v-text-field>
                  <v-text-field
                      ref="postalCode"
                      :rules="[() => !!postalCode || 'This field is required']"
                      v-model="postalCode"
                      label="ZIP / Postal Code"
                      required
                      solo-inverted
                      background-color="red lighten-2"
                  ></v-text-field>
                  <v-autocomplete
                      :filter="customFilter"
                      :rules="[() => !!country || 'This field is required']"
                      v-model="country"
                      item-text="name"
                      label="Country"
                      :items="countries"
                      required
                      solo-inverted
                  ></v-autocomplete>
                  <v-text-field
                      label="Email"
                      v-model="email"
                      :rules="emailRules"
                      required
                      solo-inverted
                      background-color="blue"
                  >
                  </v-text-field>
                  <v-text-field
                      label="Password"
                      v-model="password"
                      type="password"
                      :rules="passwordRules"
                      required
                      solo-inverted
                      background-color="blue"
                  >
                  </v-text-field>
                </v-form>
              </v-card-text>

              <v-card-actions>
                <v-btn
                    color="success"
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

  emailRules: Array<any> = [
    () => !!this.email || 'E-mail is required',
    (v: any) => /.+@.+/.test(v) || 'E-mail must be valid',
  ]

  addressRule: Array<any> = [
    () => !!this.streetName || 'This field is required',
    () => !!this.streetName && this.streetName.length <= 25 || 'Address must be less than 25 characters',
  ]

  passwordRules: Array<any> = [
    () => !!this.password || 'Password is required',
    (v: any) => (v && v.length >= 5) || 'Password must have 5+ characters',
    (v: any) => /(?=.*[A-Z])/.test(v) || 'Must have one uppercase character',
    (v: any) => /(?=.*\d)/.test(v) || 'Must have one number',
    (v: any) => /([!@$%])/.test(v) || 'Must have one special character [!@#$%]'
  ];

  customFilter (item:any, queryText:any, itemText:any) {
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