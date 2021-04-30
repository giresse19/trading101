<template>
  <div class="text-center">
    <v-snackbar
        v-model="$store.state.isFormSubmitted"
        :timeout="timeout"
    >
      {{ text }}
    </v-snackbar>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";

@Component({
  components: {},
})
export default class TheNotification extends Vue {
  @Prop() text!: string;
  timeout = 3000;
}
</script>