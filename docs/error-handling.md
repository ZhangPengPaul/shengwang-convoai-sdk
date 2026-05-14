# Error Handling

Public exceptions:

- `ConvoAiException`
- `ConvoAiAuthException`
- `ConvoAiHttpException`
- `ConvoAiSerializationException`
- `ConvoAiValidationException`

## Failure Types

- Builder validation failures throw `ConvoAiValidationException`
- Non-2xx HTTP responses throw `ConvoAiHttpException`
- JSON serialization or deserialization failures throw `ConvoAiSerializationException`
- Other transport failures are wrapped in `ConvoAiException`

## HTTP Exception Data

`ConvoAiHttpException` exposes:

- status code
- remote error code
- remote message
- raw response body
