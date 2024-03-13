<template>
    <div class="h-screen flex flex-col justify-center items-center py-52">
        <div class="font-bold text-3xl">USER-PLATFORM</div>

        <div>HELLO {{ user }}</div>
    </div>
</template>

<script setup>
const user = ref('mom')
</script>
