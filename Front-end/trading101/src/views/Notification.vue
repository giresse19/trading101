<template>
<div class="text-center">
<v-snackbar
    v-model="snackbar"
    :multi-line="multiLine"
    :timeout="timeout"
>
  {{ text }}
  <template v-slot:action="{ attrs }">
    <v-btn
        color="red"
        text
        v-bind="attrs"
        @click="snackbar = false"
    >
      Close
    </v-btn>
  </template>
</v-snackbar>
</div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";

@Component({
  components: {},
})
export default class Notification extends Vue {
  @Prop() text!: string;
  multiLine =  true;
  snackbar = false;
  timeout = 2000;
}
</script>