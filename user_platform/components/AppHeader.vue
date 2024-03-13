<template>
    <div class="sticky top-0 bg-white flex flex-row justify-between items-center md:px-6 md:py-3 px-2 py-1">
        <nuxt-link to="/" class="flex flex-row gap-1 items-center">
            <UIcon class="text-xl rotate-45" name="i-heroicons-scissors"></UIcon>
            <div class="text-xl font-semibold">Salon Search</div>
        </nuxt-link>

        <div class="flex flex-row gap-3 max-sm:hidden">
            <div v-for="nav in tabs">
                <nuxt-link class="text-gray-500 hover:text-gray-900" :to="nav.path">{{ nav.name }}</nuxt-link>
            </div>
        </div>

        <div class="max-sm:hidden">
            <UButton @click="loginDialog = true" color="black" variant="solid" class="px-4">로그인</UButton>
        </div>

        <UButton @click="loginDialog = true" class="sm:hidden" icon="i-heroicons-ellipsis-vertical" :padded="false"
            color="black" variant="link" />

        <UModal v-model="loginDialog">
            <div class="pt-2 pb-4 px-4 flex flex-col gap-2 justify-center">
                <div class="text-right">
                    <UButton size="lg" @click="loginDialog = false" icon="i-heroicons-x-mark" variant="ghost"></UButton>
                </div>
                <UInput required size="lg" v-model="emailInput" placeholder="이메일" />
                <UInput size="lg" v-model="passwordInput" placeholder="비밀번호" />
                <UButton size="lg" color="black">로그인</UButton>
                <UButton size="lg" color="yellow" @click="handleKakaoLogin">카카오톡으로 로그인하기</UButton>
                <nuxt-link to="/" class="text-sm text-center underline text-primary-200">회원가입</nuxt-link>
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
    { name: '미용실 찾기', path: '/' },
    { name: '문의하기', path: '/' },
];

function handleKakaoLogin() {
    Kakao.init('972c8d91b867f0c29eba9f84b5492831');
    console.log(Kakao.isInitialized());

    Kakao.Auth.authorize({
        redirectUri: `${window.location.origin}/kakao-callback`,
        prompt: 'login',
    });
}

</script>
