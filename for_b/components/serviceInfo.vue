<template>
    <div class="px-10 py-32">
        <div class="text-3xl font-bold py-10 text-center">미용실 관리를 위한 단 하나의 <span class="text-primary">솔루션</span></div>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div class="hover:border border-slate-700 rounded-lg px-5 py-3 flex flex-row items-center gap-5" v-for="item in infoContent">
                <UIcon class="text-4xl" :name="`i-heroicons-${item.iconName}`" />
                <div class="flex flex-col">
                    <div class="font-semibold">{{ item.label }}</div>
                    <div class="text-slate-400">{{ item.content }}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

const infoContent = ref([
    {
        label:
            '온라인 예약',
        content:
            '고객들이 웹사이트나 모바일 앱을 통해 24시간 7일 예약할 수 있게 합니다.',
        iconName: 'calendar-days'
    },
    {
        label:
            '고객 유인',
        content:
            '자동화된 알림과 개인화된 메시지로 고객들을 계속해서 유인합니다.',
        iconName: 'face-smile'
    },
    {
        label:
            '결제 처리',
        content:
            '신용카드 결제와 선지불을 받아 체크아웃을 간편하게 처리합니다.',
        iconName: 'credit-card'
    },
    {
        label:
            '메시징 플랫폼',
        content:
            '양방향 메시징을 통해 고객들과 소통하며 질문에 답변하고 관계를 구축합니다.',
        iconName: 'chat-bubble-left-ellipsis'
    },
    {
        label:
            '직원 관리',
        content:
            '일정을 생성하고 성과를 추적하며 직원들을 지원합니다.',
        iconName: 'user-group'
    },
    {
        label:
            '맞춤 브랜딩',
        content:
            '로고, 색상 및 브랜딩으로 플랫폼을 자유롭게 맞춤 설정합니다.',
        iconName: 'cog-8-tooth'
    }
])
</script>