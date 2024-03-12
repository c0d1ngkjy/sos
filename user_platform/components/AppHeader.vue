<template>
    <div
        class="sticky top-0 bg-primary-100 text-primary-800 flex flex-row justify-between items-center md:px-10 md:py-2 px-2 py-1">
        <nuxt-link to="/" class="flex flex-row gap-1 items-center">
            <UIcon class="text-xl rotate-45" name="i-heroicons-scissors"></UIcon>
            <div class="text-xl">Salon Search</div>
        </nuxt-link>

        <div class="flex flex-row gap-3 max-sm:hidden">
            <div v-for="nav in tabs">
                <nuxt-link :to="nav.path">{{ nav.name }}</nuxt-link>
            </div>
        </div>

        <div class="max-sm:hidden">
            <UButton @click="loginDialog = true" variant="outline">로그인</UButton>
        </div>

        <UButton class="sm:hidden" icon="i-heroicons-ellipsis-vertical" :padded="false" color="gray" variant="link" />

        <UModal v-model="loginDialog">
            <div class="py-10 flex flex-col gap-2 items-center ">
                <UInput size="lg" v-model="emailInput" placeholder="이메일" />
                <UInput size="lg" v-model="passwordInput" placeholder="비밀번호" />
                <UButton>로그인</UButton>
                <nuxt-link to="/rgstr" class="text-sm underline text-primary-200">회원가입</nuxt-link>
            </div>
        </UModal>
    </div>
</template>

<script setup>
const loginDialog = ref(false);
const emailInput = ref('');
const passwordInput = ref('');
const tabs = [
    { name: '홈', path: '/' },
    { name: '미용실 찾기', path: '/about' },
    { name: '문의하기', path: '/contact' },
];

</script>
