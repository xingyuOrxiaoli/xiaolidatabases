export function checkPasswordStrength(password) {
  // 密码强度规则
  const rules = [
    { regex: /.{8,}/, score: 1, message: '至少8个字符' },
    { regex: /[a-z]+/, score: 1, message: '包含小写字母' },
    { regex: /[A-Z]+/, score: 1, message: '包含大写字母' },
    { regex: /[0-9]+/, score: 1, message: '包含数字' },
    { regex: /[^a-zA-Z0-9]+/, score: 1, message: '包含特殊字符' }
  ]

  let strength = 0
  const messages = []

  rules.forEach(rule => {
    if (rule.regex.test(password)) {
      strength += rule.score
    } else {
      messages.push(rule.message)
    }
  })

  // 评估强度等级
  let level = '弱'
  if (strength >= 4) level = '强'
  else if (strength >= 2) level = '中'

  return {
    strength,
    level,
    messages
  }
}